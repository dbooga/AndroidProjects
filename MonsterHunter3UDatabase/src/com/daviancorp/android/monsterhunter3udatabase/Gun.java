package com.daviancorp.android.monsterhunter3udatabase;

public class Gun {
	private String gName;
	private String pFile;
	private String cFile;
	private String nFile;
	private Integer gDrawable;

	public Gun(String gname, String pfile, String cfile, String nfile,
			Integer gdrawable) {
		this.gName = gname;
		this.pFile = pfile;
		this.cFile = cfile;
		this.nFile = nfile;
		this.gDrawable = gdrawable;
	}

	public String getName() {
		return gName;
	}

	public String getPFile() {
		return pFile;
	}

	public String getCFile() {
		return cFile;
	}

	public String getNFile() {
		return nFile;
	}

	public Integer getDrawable() {
		return gDrawable;
	}

}
