package com.example.simpleforframe.refresh;

import in.srain.cube.request.JsonData;
import in.srain.cube.request.JsonData.JsonConverter;

/**
 * 音频记录表
 * 
 * @author dawn
 * 
 */

public class SimpeEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 123216251L;

	// 服务器音频id
	private int Audio_id;

	// 服务器视频名称
	private String Audio_name;

	// 服务器音乐名称
	private String Audio_fileName;

	// 音频作者
	private String Audio_author;

	// 音频详细介绍
	private String Audio_context;

	// 分类id
	private int Audio_sort_id;

	// 音频本地状态，1为正常0为不可用
	private int Audio_state;

	// 音频md5
	private String Audio_md5;

	private String Audio_netmd5;

	private int Audio_weight;

	public SimpeEntity() {
		super();
	}

	public int getAudio_id() {
		return Audio_id;
	}

	public void setAudio_id(int audio_id) {
		Audio_id = audio_id;
	}

	public String getAudio_name() {
		return Audio_name;
	}

	public void setAudio_name(String audio_name) {
		Audio_name = audio_name;
	}

	public String getAudio_fileName() {
		return Audio_fileName;
	}

	public void setAudio_fileName(String audio_fileName) {
		Audio_fileName = audio_fileName;
	}

	public String getAudio_author() {
		return Audio_author;
	}

	public void setAudio_author(String audio_author) {
		Audio_author = audio_author;
	}

	public String getAudio_context() {
		return Audio_context;
	}

	public void setAudio_context(String audio_context) {
		Audio_context = audio_context;
	}

	public int getAudio_sort_id() {
		return Audio_sort_id;
	}

	public void setAudio_sort_id(int audio_sort_id) {
		Audio_sort_id = audio_sort_id;
	}

	public int getAudio_state() {
		return Audio_state;
	}

	public void setAudio_state(int audio_state) {
		Audio_state = audio_state;
	}

	public String getAudio_md5() {
		return Audio_md5;
	}

	public void setAudio_md5(String audio_md5) {
		Audio_md5 = audio_md5;
	}

	public int getAudio_weight() {
		return Audio_weight;
	}

	public void setAudio_weight(int audio_weight) {
		Audio_weight = audio_weight;
	}

	public SimpeEntity(int audio_id, String audio_name, String audio_fileName,
			String audio_author, String audio_context, int audio_sort_id,
			int audio_state, String audio_md5, String audio_netmd5,
			int audio_weight) {
		super();
		Audio_id = audio_id;
		Audio_name = audio_name;
		Audio_fileName = audio_fileName;
		Audio_author = audio_author;
		Audio_context = audio_context;
		Audio_sort_id = audio_sort_id;
		Audio_state = audio_state;
		Audio_md5 = audio_md5;
		Audio_netmd5 = audio_netmd5;
		Audio_weight = audio_weight;
	}

	public String getAudio_netmd5() {
		return Audio_netmd5;
	}

	public void setAudio_netmd5(String audio_netmd5) {
		Audio_netmd5 = audio_netmd5;
	}

	public static JsonConverter<SimpeEntity> jsonConverter = new JsonConverter<SimpeEntity>() {
		@Override
		public SimpeEntity convert(JsonData item) {
			SimpeEntity audioEntity = new SimpeEntity();
			audioEntity.setAudio_id(item.optInt("id"));
			audioEntity.setAudio_context(item.optString("audio_context"));
			audioEntity.setAudio_fileName(item.optString("audio_audioname"));
			audioEntity.setAudio_name(item.optString("audio_name"));
			audioEntity.setAudio_author(item.optString("audio_author"));
			audioEntity.setAudio_sort_id(item.optInt("audio_sort_id"));
			audioEntity.setAudio_state(item.optInt("audio_state"));
			audioEntity.setAudio_weight(item.optInt("weight"));
			audioEntity.setAudio_netmd5(item.optString("mb_md5"));
			return audioEntity;
		}
	};

}
