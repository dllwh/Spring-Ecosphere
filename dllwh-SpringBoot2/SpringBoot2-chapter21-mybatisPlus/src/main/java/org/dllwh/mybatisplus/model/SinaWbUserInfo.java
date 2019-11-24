package org.dllwh.mybatisplus.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述:新浪微博用户信息
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019-11-04 12:12:17 AM
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Data
public class SinaWbUserInfo implements Serializable {
	private static final long serialVersionUID = -1135006040668909346L;
	/**
	 * 用户信息
	 */
	private userInfo userInfo;
	/**
	 * 页签信息
	 */
	private tabsInfo tabsInfo;

	/**
	 * 
	 * 把今天最好的表现当作明天最新的起点．．～
	 *
	 * Today the best performance as tomorrow newest starter!
	 *
	 * @类描述: 用户信息
	 * @创建者: 独泪了无痕--duleilewuhen@sina.com
	 * @创建时间: 2019-11-04 10:51:04 PM
	 * @版本: V1.0.1
	 * @since: JDK 1.8
	 */
	@Data
	public class userInfo {
		/** 用户UID */
		private long id;
		/** 用户昵称 */
		private String screen_name;
		/** 用户头像地址（中图），50×50像素 */
		private String profile_image_url;
		/** 用户的微博统一URL地址 */
		private String profile_url;
		/** 微博数 */
		private int statuses_count;
		/** 是否是微博认证用户，即加V用户，true：是，false：否 */
		private boolean verified;
		private int verified_type;
		private int verified_type_ext;
		/** 认证原因 */
		private String verified_reason;
		/** */
		private boolean close_blue_v;
		/** 用户个人描述 */
		private String description;
		/** 性别，m：男、f：女、n：未知 */
		private String gender;
		/** */
		private int mbtype;
		/** */
		private int urank;
		/** */
		private int mbrank;
		/** 该用户是否关注当前登录用户，true：是，false：否 */
		private boolean follow_me;
		/** */
		private boolean following;
		/** 粉丝数 */
		private int followers_count;
		/** */
		private int follow_count;
		/** */
		private String cover_image_phone;
		/** 用户头像地址（高清），高清头像原图 */
		private String avatar_hd;
		/** 用户头像地址（大图），180×180像素 */
		private String avatar_large;
		/** 关注数 */
		private int friends_count;
		/** 收藏数 */
		private int favourites_count;
		/** 用户创建（注册）时间 */
		private int created_at;
	}

	@Data
	public class tabsInfo {
		private List<tabInfo> tabs;

		/**
		 * 
		 * 把今天最好的表现当作明天最新的起点．．～
		 *
		 * Today the best performance as tomorrow newest starter!
		 *
		 * @类描述: 用户标签信息
		 * @创建者: 独泪了无痕--duleilewuhen@sina.com
		 * @创建时间: 2019-11-04 10:51:22 PM
		 * @版本: V1.0.1
		 * @since: JDK 1.8
		 */
		@Data
		public class tabInfo {
			private int id;
			/** 页签key */
			private String tabKey;
			/** 是否是必显示 */
			private int must_show;
			/** 是否隐藏 */
			private int hidden;
			/** 页签标题 */
			private String title;
			/** 页签类型 */
			private String tab_type;
			/***/
			private String containerid;
		}
	}
}
