public class mutlithreadFibonacci
{

  public static void main(String args[])
  {
    Worker workerOne = new Worker("FibThread One");
    Thread threadOne = new Thread(workerOne);
    threadOne.start();

    Worker workerTwo = new Worker("FibThread Two");
    Thread threadTwo = new Thread(workerTwo);
    threadTwo.start();

    int iterations = 0;
    while (threadOne.isAlive() && threadTwo.isAlive())
    {
      System.out.println(workerOne);
      System.out.println(workerTwo);
      iterations++;
      if (iterations >= 10)
      {
        workerOne.die();
        workerTwo.die();
      }
      try
      {
        Thread.sleep(2000);
      }
      catch (Exception error)
      {
        System.out.println("Thread had too much caffeine, could not sleep");
      }
    }
    System.out.println("Program Exit");
    System.exit(1);
  }
}

class Worker implements Runnable
{
  private final String NAME;
  private long step = 0;
  private long y = 1;
  private long x = 1;
  private long z;
  private volatile boolean shouldBeAlive = true;

  public Worker(String NAME)
  {
    this.NAME = NAME;
  }

  @Override
  public void run()
  {
    while (true)
    {
      if (shouldBeAlive) nextStep();
      else
      {
        System.out.println("Thread" + NAME + " has died");
        break;
        //System.exit(1);
      }
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

  public void die()
  {
    shouldBeAlive = false;
  }

  public synchronized String toString()
  {
    return NAME + " Step#: " + step + " z: "+z+" x: "+x+" y: "+y;
  }
}