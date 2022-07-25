
public class Quadtree {

	public Quadtree one;
	public Quadtree two;
	public Quadtree three;
	public Quadtree four;
	public int xmin;
	public int xmax;
	public int ymin;
	public int ymax;
	boolean leaf;
	boolean div = false;
	int x;
	int y;

	public Quadtree(int xmin, int xmax, int ymin, int ymax) {
		this.xmin = xmin;
		this.xmax = xmax;
		this.ymin = ymin;
		this.ymax = ymax;
		this.one = null;
		this.two = null;
		this.three = null;
		this.four = null;
		this.leaf = true;
 	}

	public void subdivide(Quadtree p) {

		p.one = new Quadtree(p.xmin, (p.xmin + p.ymax) / 2, p.ymin, (p.ymin + p.ymax) / 2);
		p.two = new Quadtree((p.xmin + p.xmax) / 2, p.xmax, p.ymin, (p.ymin + p.ymax) / 2);
		p.three = new Quadtree(p.xmin, (p.xmin + p.xmax) / 2, (p.ymin + p.ymax) / 2, p.ymax);
		p.four = new Quadtree((p.xmin + p.xmax) / 2, p.xmax, (p.ymin + p.ymax)/2, p.ymax);
	}

	int cur_lvl=0;
	int lvl;
	
	public Point findNode(Point p,Quadtree root) {
		lvl=0;
		cur_lvl=0;
		Point result=findNode2(root,p);
		return result;
		
	}
	
	
	
	public Point findNode2(Quadtree focusNode , Point p) {
		lvl= cur_lvl;
		
		
		
		if ((focusNode==null)||(focusNode.four==null)||(focusNode.three==null)||(focusNode.two==null)||(focusNode.one==null)) {
			return null;
		}
		
		if ((focusNode.x==p.x)&&(focusNode.y==p.y)||(focusNode.one.x==p.x)&&(focusNode.one.y==p.y)||(focusNode.two.x==p.x)&&(focusNode.two.y==p.y)||(focusNode.three.x==p.x)&&(focusNode.three.y==p.y)||(focusNode.four.x==p.x)&&(focusNode.four.y==p.y))
			return p;
		
		if((focusNode.x==-1)&&(focusNode.y==-1)) {
			
			if ((p.x <= focusNode.one.xmax) && ((p.x > focusNode.one.xmin)) && (((p.y <= focusNode.one.ymax) && (p.y > focusNode.one.ymin)))) {
				focusNode=focusNode.one;
				cur_lvl++;
				Point result=findNode2(focusNode,p);
				return result;
			}
			if ((p.x <= focusNode.two.xmax) && ((p.x > focusNode.two.xmin)) && (((p.y <= focusNode.two.ymax) && (p.y > focusNode.two.ymin)))) {
				focusNode=focusNode.two;
				cur_lvl++;
				Point result=findNode2(focusNode,p);
				return result;
			}
			if ((p.x <= focusNode.three.xmax) && ((p.x > focusNode.three.xmin)) && (((p.y <= focusNode.three.ymax) && (p.y > focusNode.three.ymin))))
			{
				focusNode=focusNode.three;
				cur_lvl++;
				Point result=findNode2(focusNode,p);
				return result;
			}
			if ((p.x <= focusNode.four.xmax) && ((p.x > focusNode.four.xmin)) && (((p.y <= focusNode.four.ymax) && (p.y > focusNode.four.ymin)))) {
				focusNode=focusNode.four;
				cur_lvl++;
				Point result=findNode2(focusNode,p);
				return result;
			}
			
		}
		
		
		return null;
		
		
		
	}
	
	
	

	public void insert(Point point, Quadtree nd) {

		if (((point.getX() <= nd.xmax) && (point.getX() > nd.xmin))
				&& ((point.getY() <= nd.ymax) && (point.getY() > nd.ymin))) {
			if (nd.leaf == true) {
				nd.x = point.getX();
				nd.y = point.getY();
				nd.leaf = false;
				return;
			}

			else {

				if (nd.div == false) {
					this.subdivide(nd);
					nd.div = true;

					if (nd.x != -1 && nd.y != -1) {
						if ((nd.x <= nd.one.xmax) && ((nd.x > nd.one.xmin))
								&& (((nd.y <= nd.one.ymax) && (nd.y > nd.one.ymin)))) {
							nd.one.x = nd.x;
							nd.one.y = nd.y;
							nd.one.leaf = false;
							nd.x = -1;
							nd.y = -1;
						}

						else if (((nd.x <= nd.two.xmax) && (nd.x > nd.two.xmin))
								&& ((nd.y <= nd.two.ymax) && (nd.y > nd.two.ymin))) {
							nd.two.x = nd.x;
							nd.two.leaf = false;
							nd.two.y = nd.y;
							nd.x = -1;
							nd.y = -1;
						}

						else if (((nd.x <= nd.three.xmax) && (nd.x > nd.three.xmin))
								&& ((nd.y <= nd.three.ymax) && (nd.y > nd.three.ymin))) {
							nd.three.x = nd.x;
							nd.three.leaf = false;
							nd.three.y = nd.y;
							nd.x = -1;
							nd.y = -1;
						}

						else if (((nd.x <= nd.four.xmax) && (nd.x > nd.four.xmin))
								&& ((nd.y <= nd.four.ymax) && (nd.y > nd.four.ymin))) {
							nd.four.x = nd.x;
							nd.four.leaf = false;
							nd.four.y = nd.y;
							nd.x = -1;
							nd.y = -1;
							
						}
					}
				}
			}
		}

		if ((((point.getX() <= nd.two.xmax) && (point.getX() > nd.two.xmin))
				&& ((point.getY() <= nd.two.ymax) && (point.getY() > nd.two.ymin)))) {

			insert(point, nd.two);

		}

		else if (((point.getX() <= nd.one.xmax) && (point.getX() > nd.one.xmin))
				&& ((point.getY() <= nd.one.ymax) && (point.getY() > nd.one.ymin))) {
			insert(point, nd.one);
		}

		else if (((point.getX() <= nd.three.xmax) && (point.getX() > nd.three.xmin))
				&& ((point.getY() <= nd.three.ymax) && (point.getY() > nd.three.ymin))) {
			insert(point, nd.three);
		}

		else if (((point.getX() <= nd.four.xmax) && (point.getX() > nd.four.xmin))
				&& ((point.getY() <= nd.four.ymax) && (point.getY() > nd.four.ymin))) {

			insert(point, nd.four);
		}

		return;
	}

}
