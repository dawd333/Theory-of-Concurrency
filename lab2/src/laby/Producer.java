package laby;

class Producer extends Thread {
    private final Buffer _buf;

    //Random dla funkcji sleep do zrealizowania problemu ograniczonego bufora 1c
//    Random random = new Random();

    public Producer(Buffer _buf){
        this._buf = _buf;
    }

    public void run() {
        for (int i = 0; i < 100; ++i) {

            //sleep do zrealizowania problemu ograniczonego bufora 1c
//            int n = random.nextInt(400) + 100;
//            try {
//                sleep(n);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            _buf.put(i);
        }
    }
}
