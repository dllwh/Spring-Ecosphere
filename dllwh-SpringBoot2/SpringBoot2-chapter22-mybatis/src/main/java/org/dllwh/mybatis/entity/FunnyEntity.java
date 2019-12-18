package org.dllwh.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 数据传输对象
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019-10-13 3:02:05 PM
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Data
public class FunnyEntity implements Serializable {

	private static final long serialVersionUID = -5537520595297073747L;
	private Integer           id;
	/***/
	private String            articleGenre;
	/***/
	private Date              behotTime;
	/***/
	private String            chineseTag;
	/***/
	private String            groupId;
	/***/
	private String            hasGallery;
	/***/
	private String            imageUrl;
	/***/
	private String            isFeedAd;
	/***/
	private String            mediaAvatarUrl;
	/***/
	private String            mediaUrl;
	/***/
	private String            middleMode;
	/***/
	private String            moreMode;
	/***/
	private String            singleMode;
	/***/
	private String            source;
	/***/
	private String            sourceUrl;
	/***/
	private String            tag;
	/***/
	private String            tagUrl;
	/***/
	private String            title;
	/***/
	private String            commentsCount;
	/***/
	private String            document;
}
