import java.util.Random;
public class Main {

	public static void main(String[] args) {
		
		Random smth = new Random();

		Quadtree qt = new Quadtree(0, 65536, 0, 65536);
		qt.x=-1;
		qt.y=-1;
	 	qt.leaf=false;
		
	 	int sizeOftree = 10000;
		
		Point p = new Point(0,0);
		
		for(int i = 0;i<sizeOftree;i++)
		{
			int x = smth.nextInt(65536);
			int y = smth.nextInt(65536);
			p.setX(x);
			p.setY(y);
			qt.insert(p,qt);
		}
		
		
		
		float sumFailures = 0;
		float numOfFails = 0;
		float SumFind = 0;
		float sumOfFind = 0;

		while ((numOfFails != 100) || (sumOfFind != 100)) {

			int y = smth.nextInt(65536);
			int y1 = smth.nextInt(65536);
			Point p1 = new Point (y,y1);

			Point result = qt.findNode(p1,qt);

			if ((result == null) && (numOfFails != 100)) {
				sumFailures = sumFailures + qt.lvl;
				numOfFails++;
			}

			if ((result != null) && (sumOfFind != 100)) {
				SumFind = SumFind + qt.lvl;
				System.out.println("Found until now : " + sumOfFind);
				sumOfFind++;
			}
		}

		System.out.println("Average of Found     : " + SumFind / 100);
		System.out.println("Average of Not Found : " +sumFailures / 100);

		
	}

}
