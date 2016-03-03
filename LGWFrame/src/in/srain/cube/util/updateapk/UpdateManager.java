package in.srain.cube.util.updateapk;

import java.io.File;

import org.json.JSONObject;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lt.ltframe.R;
import com.panfeng.shining.slw.net.xUtilsNet;
import com.panfeng.shining.tools.FileTools;
import com.pangfeng.frame.application.DefindConstant;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;



public class UpdateManager {

	static String getSavePath(int vc) {
		Context context = ContextKeeper.getInstance();
		if (Environment.getExternalStorageDirectory().exists()) {
			File path = new File(Environment.getExternalStorageDirectory(),
					context.getPackageName() + "/v" + vc + "update.apk");
			path.getParentFile().mkdirs();
			return path.getAbsolutePath();

		} else {
			File path = new File(context.getFilesDir(),
					context.getPackageName() + "/v" + vc + "update.apk");
			path.getParentFile().mkdirs();
			return path.getAbsolutePath();
		}

	}

	static void updateProgress(final TextView txt, final ProgressBar pro,
			final String info, final int progress) {
		ContextKeeper.getHandler().post(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				txt.setText(info);
				pro.setProgress(progress);
			}
		});
	}

	static public void installApk(String aFile) {
		Context context = ContextKeeper.getInstance();
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(new File(aFile)),
				"application/vnd.android.package-archive");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

	static public boolean checkNewVersion(StringBuffer sb) {
		// String pname = TyuContextKeeper.getInstance().getPackageName();
		// int type = 0;
		// if (pname.equals("com.vchuang.tyuvandriver")) {
		// type = 1;
		// }
		String url = DefindConstant.HOST + "smc/swupdate";
		final String res = HttpClientUtils.postInfo(url, "");
		if (HttpClientUtils.isValidResult(res)) {
			try {
				PackageInfo pack_info = ContextKeeper
						.getInstance()
						.getPackageManager()
						.getPackageInfo(
								ContextKeeper.getInstance().getPackageName(),
								0);
				JSONObject jobj = new JSONObject(res);
				if (jobj.getInt("vcode") > pack_info.versionCode) {
					sb.append(jobj.toString());
					return true;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	static public void showUpdateInfo(final Activity aParent, String aJson)
			throws Exception {

		Toast.makeText(aParent, aParent + "", Toast.LENGTH_LONG);

		JSONObject obj = new JSONObject(aJson);
		View aboutView = aParent.getLayoutInflater().inflate(
				R.layout.update_view, null);
		final Dialog dialog = new Dialog(aParent, R.style.selectorDialog);
		PackageInfo pack_info = aParent.getPackageManager().getPackageInfo(
				aParent.getPackageName(), 0);
		dialog.setCanceledOnTouchOutside(false);
		final TextView info = (TextView) aboutView.findViewById(R.id.info);
		final ProgressBar pb = (ProgressBar) aboutView
				.findViewById(R.id.progressBar1);
		final int vcode = obj.getInt("vcode");
		if (vcode > pack_info.versionCode) {
			final String url = obj.getString("downpath");
			info.setText(String.format("发现新版本，版本号%s:\r\n%s",
					obj.getString("vname"), obj.getString("content")));
			final Button btn_sure = (Button) aboutView
					.findViewById(R.id.dialog_sure);
			final Button btn_cancel = (Button) aboutView
					.findViewById(R.id.dialog_cancel);

			final HttpHandler<File> handler = null;
			btn_cancel.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (dialog != null && dialog.isShowing())
						dialog.dismiss();
				}
			});
			btn_sure.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

					xUtilsNet.getVideo(handler, new RequestCallBack<File>(10) {
						@Override
						public void onSuccess(ResponseInfo<File> responseInfo) {
							
							dialog.dismiss();
							installApk(getSavePath(vcode));

						}

						public void onStart() {
							super.onStart();

							info.setText("开始下载:0%");
							pb.setVisibility(View.VISIBLE);
							btn_sure.setVisibility(View.GONE);

						}

						public void onLoading(long total, long current,
								boolean isUploading) {
							super.onLoading(total, current, isUploading);

							pb.setVisibility(View.VISIBLE);
							double current_ = current;
							double total_ = total;
							int progress = (int) ((current_ / total_) * 100);
							info.setText("开始下载:" + progress + "%");
							pb.setProgress(progress);
						}

						@Override
						public void onFailure(HttpException error, String msg1) {

							if (new File(getSavePath(vcode)).exists()) {
								FileTools fs = new FileTools();
								fs.deleteFolderFile(getSavePath(vcode), true);
							}

						}
					}, getSavePath(vcode), url);

				}
			});
		} else {
			info.setText("您当前已经是最新版本");
			aboutView.findViewById(R.id.dialog_sure).setOnClickListener(
					new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					});
			aboutView.findViewById(R.id.dialog_cancel).setOnClickListener(
					new OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							if (dialog != null && dialog.isShowing())
								dialog.dismiss();
						}
					});
		}

		dialog.setContentView(aboutView);
		dialog.show();
	}

	static public void showUpdateDialog(final Activity aParent) {
		final ProgressDialog dlg = new ProgressDialog(aParent);
		dlg.setMessage("正在检查更新");
		dlg.show();
		new Thread() {
			@Override
			public void run() {
				// String pname =
				// TyuContextKeeper.getInstance().getPackageName();
				String url = DefindConstant.HOST + "smc/swupdate";
				final String res = HttpClientUtils.postInfo(url, "");
				if (dlg != null && dlg.isShowing())
					dlg.dismiss();
				if (HttpClientUtils.isValidResult(res)) {
					ContextKeeper.getHandler().post(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							try {
								showUpdateInfo(aParent, res);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				}
			};
		}.start();
	}
}
