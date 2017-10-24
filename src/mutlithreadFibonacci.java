public class mutlithreadFibonacci
{
  //***********************************
  //Justin Stoner
  //
  //Starts the worker threads and checks on them every 2 seconds
  //kills the worker threads and exits after 10 checks(20 seconds)
  //***********************************

  //***********************************
  //The main
  //Inputs: String args[]
  //Returns: void
  //Starts the worker threads and checks on them every 2 seconds
  //kills the worker threads and exits after 10 checks(20 seconds)
  //**********************************
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
        workerOne.stop();
        workerTwo.stop();
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
  //***********************************
  //Justin Stoner
  //
  //Calculates fibonacci values on it's own thread until it is told to stop
  //***********************************

  private final String NAME;
  private long step = 0;
  private long y = 1;
  private long x = 1;
  private long z;
  private volatile boolean shouldBeAlive = true;

  //***********************************
  //The constructor for the worker threads
  //Inputs: String NAME
  //Returns: NA
  //Sets the worker's name to the given strings
  //**********************************
  public Worker(String NAME)
  {
    this.NAME = NAME;
  }

  //***********************************
  //Represents the thread, sort of like a main
  //Inputs: void
  //Returns: void
  //tells the class to calculate fibonacci values until it is told to stop
  //**********************************
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

  //***********************************
  //The calculator
  //Inputs: void
  //Returns: void
  //Calculates the next number in the fibonacci sequence
  //**********************************
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
      step = 1;
    }
  }

  //***********************************
  //Kill function
  //Inputs: void
  //Returns: void
  //Tells the worker to stop running
  //**********************************
  public void stop()
  {
    shouldBeAlive = false;
  }

  //***********************************
  //toString function
  //Inputs: void
  //Returns: String
  //Returns the name, step number, and the numbers relevant to that step
  //**********************************
  public synchronized String toString()
  {
    return NAME + " ,Step#: " + step + " ,z: "+z+" ,x: "+x+" ,y: "+y;
  }
}