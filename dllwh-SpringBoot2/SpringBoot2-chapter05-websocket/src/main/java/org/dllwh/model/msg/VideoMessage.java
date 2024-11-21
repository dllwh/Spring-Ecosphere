package org.dllwh.model.msg;

import org.dllwh.model.BaseMessage;
import org.dllwh.model.FileInfo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 视频类型消息
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2020-01-24
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class VideoMessage extends BaseMessage {
	private static final long serialVersionUID = -6740744771586797075L;
	/** 视频附件大小（单位：字节） */
	private long fileLength;
	/** 视频名称，带图片格式 */
	private long fileName;
	/** 在上传视频后会返回字符串，只有含有secret才能够下载此图片 */
	private long secret;
	/** 视频播放长度（单位：秒） */
	private long length;
	/** 视频缩略图属性，包括长、宽尺寸 */
	private FileInfo fileInfo;
	/** 上传视频缩略图远程地址，在上传视频缩略图后会返回 */
	private long thumb;
	/** 上传视频地址，在上传成功后会返回 */
	private long url;
}
