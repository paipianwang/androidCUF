package in.srain.cube.util.updateapk;

public interface DownloadListener {
	void onProgressChanged(String name,int progress);
	void onSucess(String name);
	void onError(String error);
}
