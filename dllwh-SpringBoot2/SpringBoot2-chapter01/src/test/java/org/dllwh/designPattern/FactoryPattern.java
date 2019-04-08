package org.dllwh.designPattern;

public class FactoryPattern {
	// 抽象的接口
	abstract interface Car {
		public abstract void run();

		public abstract void stop();
	}

	// 具体实现类
	static class Benz implements Car {
		public void run() {
			System.out.println("Benz开始启动了。。。。。");
		}

		public void stop() {
			System.out.println("Benz停车了。。。。。");
		}
	}

	// 具体实现类
	static class Ford implements Car {
		public void run() {
			System.out.println("Ford开始启动了。。。");
		}

		public void stop() {
			System.out.println("Ford停车了。。。。");
		}
	}

	// 工厂类
	static class Factory {
		public static Car getCarInstance(String type) {
			Car c = null;
			if ("Benz".equals(type)) {
				c = new Benz();
			}
			if ("Ford".equals(type)) {
				c = new Ford();
			}
			return c;
		}
	}

	public static void main(String[] args) {
		Car c = Factory.getCarInstance("Benz");
		if (c != null) {
			c.run();
			c.stop();
		} else {
			System.out.println("造不了这种汽车。。。");
		}
	}
}