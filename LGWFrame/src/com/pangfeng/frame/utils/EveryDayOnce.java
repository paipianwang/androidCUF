package com.pangfeng.frame.utils;

import com.pangfeng.frame.utils.interfaces.EveryDayCallBack;

public final class EveryDayOnce {

	public static <T> T submit(EveryDayCallBack<T> everyDayCallBack) {
		return Psubmit(everyDayCallBack);
	}
	
	
	private static <T> T Psubmit(EveryDayCallBack<T> everyDayCallBack) {
		if (true) {
			everyDayCallBack.execute();
		} else
			return null;
		return null;
	}

}
