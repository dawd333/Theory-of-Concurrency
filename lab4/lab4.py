import pprint
import networkx as nx
import matplotlib.pyplot as plt

productions = {}
A = []
w = ""
with open('dane.txt', 'r') as file:  # pobranie danych z pliku: produkcje, alfabet i ślad
    lines = file.readlines()
    for index in range(lines.__len__()-2):
        lines[index] = lines[index][:-1]
        productions[lines[index][0]] = lines[index][3:]
    for letter in lines[lines.__len__()-2][2:]:
        if letter == '{' or letter == '}' or letter == ',' or letter == '\n':
            pass
        else:
            A.append(letter)
    w = lines[-1][2:]
print("Produkcje:", productions)
print("A:", A)
print("w:", w)


# Wyznaczanie relacji zależności D oraz relacji niezależności I
D = set()
I = set()
for i in range(productions.__len__()):
    current_key = list(productions.keys())[i]
    for j in range(productions.__len__()):
        tmp_key = list(productions.keys())[j]
        if list(productions.get(tmp_key))[0] in productions.get(current_key) or list(productions.get(current_key))[0] in productions.get(tmp_key):
            D.add((current_key, tmp_key))
            D.add((tmp_key, current_key))
        else:
            I.add((current_key, tmp_key))
            I.add((tmp_key, current_key))
print("D:", D)
print("I:", I)

# Wyznaczanie postaci normalnej Foaty FNF([w]) śladu [w]
stacks = [[] for i in range(A.__len__())]  # tworzę stosy na każdą litere alfabetu
for letter in w[::-1]:  # iteruję po każdej literze badanego śladu od tyłu
    index_of_letter = A.index(letter)  # zbieram na której pozycji w alfabecie jest dana litera
    for tmp_letter in A:  # iteracja po wszystkich literach alfabetu
        if tmp_letter == letter:  # dopisuję literę na swój stos
            stacks[index_of_letter].append(letter)
        elif D.__contains__((letter, tmp_letter)):  # jeśli badana para występuje w D
            index_of_tmp_letter = A.index(tmp_letter)  # zbieram na której pozycji w alfabecie jest dana litera
            stacks[index_of_tmp_letter].append('*')  # dopisuje marker na odpowiedni stos

FNF_w = ""
while stacks != [[] for i in range(A.__len__())]:  # dopóki stosy nie będą puste
    FNF_w = FNF_w + '('  # odpowiednie formatowanie postaci normalnej foaty
    top_of_stack = []
    for i in range(A.__len__()):
        if stacks[i] == []:
            top_of_stack.append('')  # jesli jest pusty
        else:
            top_of_stack.append(stacks[i][-1])  # zapisuje co jest na górze każdego stosu
    for top_char in top_of_stack:  # dla każdej litery, która jest na górze stosu
        if top_char == '*':  # jeśli marker to pomijamy
            pass
        else:
            FNF_w = FNF_w + top_char  # jeśli litera to dopisuje
            for tmp_letter in A:  # usuwam markery z odpowiednich stosów
                if D.__contains__((top_char, tmp_letter)):
                    stacks[A.index(tmp_letter)].pop()
    if FNF_w[-1] == '(':  # odpowiednie formatowanie postaci normalnej foaty
        FNF_w = FNF_w[:-1]
    else:
        FNF_w = FNF_w + ')'
print("FNF([w]):", FNF_w)

# Wyliczenie grafu zależności w postaci minimalnej dla słowa w
# utworzenie pełnego acyklicznego grafu zależności (razem z tymi redundantnymi)
dependence_graph = [[0 for x in range(w.__len__())] for y in range(w.__len__())]
for y in range(w.__len__()):
    for x in range(y+1, w.__len__(), 1):
        if D.__contains__((w[y], w[x])):
            dependence_graph[y][x] = 1

# usunięcie redundantnych zależności przy pomocy transitive reduction, cubic algorithm dla grafów acyklicznych skierowanych
for y in range(w.__len__()):
    for x in range(w.__len__()):
        if dependence_graph[y][x] == 1:
            for z in range(w.__len__()):
                if dependence_graph[x][z] == 1:
                    dependence_graph[y][z] = 0
print("Macierz reprezentująca minimalny graf zależności dla słowa w:")
pprint.pprint(dependence_graph)

# Rysowanie grafu zależności w postaci minimalnej dla słowa w przy pomocy networkx i matplotliba
graph = nx.DiGraph()
for i in range(w.__len__()):
    graph.add_node(w[i]+str(i+1))
for y in range(w.__len__()):
    for x in range(w.__len__()):
        if dependence_graph[y][x] == 1:
            graph.add_edge(w[y]+str(y+1), w[x]+str(x+1))
pos = nx.circular_layout(graph)
nx.draw(graph, pos, with_labels=True)
plt.show()
plt.savefig('graph.png')

# Wyznaczanie postaci normalnej Foaty na podstawie grafu
FNF = ""  # zmienna na postać normalną foaty z grafu
while dependence_graph:  # sortowanie topologiczne grafu w celu uzyskania postaci normalnej foaty z grafu
    nodes_degrees = []  # lista ze stopniami wierzchołków grafu oraz jej wypełnienie
    for y in range(dependence_graph.__len__()):
        nodes_degrees.append(0)
        for x in range(dependence_graph[0].__len__()):
            if dependence_graph[x][y] == 1:
                nodes_degrees[-1] = nodes_degrees[-1]+1
    FNF = FNF + '('  # odpowiednie formatowanie postaci normalnej foaty
    number_of_deletes = 0  # zmienna ile w danej iteracji wycieliśmy już wierzchołków, aby korygować indeksy w grafie / śladzie
    for node_index in range(nodes_degrees.__len__()):
        if nodes_degrees[node_index] == 0:  # jeśli stopień wierzchołka grafu = 0 to wycinamy i dodajemy do danej klasy foaty
            FNF = FNF + w[node_index-number_of_deletes]  # dopisanie wierzchołka do danej klasy foaty
            w = w[:node_index-number_of_deletes]+w[node_index-number_of_deletes+1:]  # obcięcie śladu
            for i in range(dependence_graph.__len__()):  # obcięcie macierzy reprezentującej graf z wycinanego wierzchołka
                dependence_graph[i] = dependence_graph[i][:node_index-number_of_deletes] + dependence_graph[i][node_index-number_of_deletes+1:]
            dependence_graph = dependence_graph[:node_index-number_of_deletes] + dependence_graph[node_index-number_of_deletes+1:]
            number_of_deletes = number_of_deletes+1  # zwiększenie indeksu wyciętych wierzchołków
    FNF = FNF + ')'  # odpowiednie formatowanie postaci normalnej foaty
print("FNF([w]):", FNF)
