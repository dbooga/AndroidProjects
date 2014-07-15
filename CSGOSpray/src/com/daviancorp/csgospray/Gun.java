package com.daviancorp.csgospray;

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

	public String getgName() {
		return gName;
	}

	public String getpFile() {
		return pFile;
	}

	public String getcFile() {
		return cFile;
	}

	public String getnFile() {
		return nFile;
	}

	public Integer getgDrawable() {
		return gDrawable;
	}

}
