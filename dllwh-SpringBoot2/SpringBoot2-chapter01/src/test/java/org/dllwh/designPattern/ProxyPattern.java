package org.dllwh.designPattern;

public class ProxyPattern {

	/**
	 * @类描述: 代理接口
	 */
	static interface ProxyInterface {
		// 需要代理的是结婚这件事
		void marry();
	}

	static class WeddingCompany implements ProxyInterface {
		private ProxyInterface proxyInterface;

		public WeddingCompany(ProxyInterface proxyInterface) {
			this.proxyInterface = proxyInterface;
		}

		public void marry() {
			System.out.println("我们是婚庆公司代理结婚");
			proxyInterface.marry();
		}
	}

	static class NormalHome implements ProxyInterface {

		public void marry() {
			System.out.println("我们结婚啦～");
		}
	}

	public static void main(String[] args) {
		ProxyInterface proxyInterface = new WeddingCompany(new NormalHome());
		proxyInterface.marry();
	}
}