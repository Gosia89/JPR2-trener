package mod05.ex04;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Point {
	private int x;
	private int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	private double length(Point p) {
		int dx = this.x - p.x;
		int dy = this.y - p.y;
		return Math.sqrt(dx * dx + dy * dy);
	}

	public void directInvocation(Point p2) {
		System.out.printf(
				"Distance from %s to %s = %.2f [direct invocation]%n", this,
				p2, length(p2));
	}

	// ------------------------------------------------------------------
	// REFLECTION
	// ------------------------------------------------------------------

	public void reflectiveInvocation(Point p2) {
		Method r = null;
		Class<?>[] argTypes = new Class[] { Point.class };
		try {
			r = Point.class.getDeclaredMethod("length", argTypes);
			// meth.setAccessible(true);
			System.out.printf(
					"Distance from %s to %s = %.2f [reflective invocation]%n",
					this, p2, r.invoke(this, p2));
		} catch (NoSuchMethodException 
				| SecurityException
				| IllegalAccessException 
				| IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}

	}

	// ------------------------------------------------------------------
	// PROXY
	// ------------------------------------------------------------------
	public static class PointProxy {
		private PointProxy() {
		}

		public double invoke(Point p1, Point p2) {
			return p1.length(p2);
		}
	}

	public void proxyInvocation(Point p2) {
		PointProxy proxy = new PointProxy();

		System.out.printf("Distance from %s to %s = %.2f [proxy invocation]%n",
				this, p2, proxy.invoke(this, p2));
	}

	// ------------------------------------------------------------------
	// METHOD HANDLE
	// ------------------------------------------------------------------

	public void methodHandleInvocation(Point p2) {
		MethodHandle mh;
		MethodType desc = MethodType.methodType(double.class, Point.class);
		try {
			mh = MethodHandles.lookup()
					.findVirtual(Point.class, "length", desc);
			System.out.printf("Distance from %s to %s = %.2f [method handle]%n", this,
					p2, mh.invoke(this, p2));
		} catch (NoSuchMethodException | IllegalAccessException e) {
			throw (AssertionError) new AssertionError().initCause(e);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	// ------------------------------------------------------------------
	public static void main(String[] args) {
		Point p1 = new Point(2, 4);
		Point p2 = new Point(5, 0);
		p1.directInvocation(p2);
		p1.reflectiveInvocation(p2);
		p1.proxyInvocation(p2);
		p1.methodHandleInvocation(p2);
	}
}
