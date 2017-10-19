public class mutlithreadFibonacci
{

  public static void main(String args[])
  {
    Worker workerOne = new Worker("One");
    Thread threadOne = new Thread(workerOne);
    threadOne.start();

    Worker workerTwo = new Worker("Two");
    Thread threadTwo = new Thread(workerTwo);
    threadTwo.start();

    while (threadOne.isAlive() && threadTwo.isAlive())
    {
      System.out.println(workerOne);
      System.out.println(workerTwo);
    }

  }
}

class Worker implements Runnable
{
  String oshaGuideLines;

  private final String NAME;
  private long step = 0;
  private long y = 1;
  private long x = 1;
  private long z = x + y;

  public Worker(String NAME)
  {
    this.NAME = NAME;
  }

  @Override
  public void run()
  {
    while (true)
    {
      nextStep();
    }
  }

  public synchronized void nextStep()
  {
    step++;

    x = y;
    y = z;
    z = x + y;

    if (z == 7540113804746346429L)
    {
      z = 2;
      x = 1;
      y = 1;
    }
  }

  public synchronized String toString()
  {
    return NAME + " z: "+z+" x: "+x+" y: "+y;
  }
}