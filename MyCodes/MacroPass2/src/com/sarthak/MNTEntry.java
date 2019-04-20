package com.sarthak;

public class MNTEntry {

	String macroname;
	int kp,pp,kpdtp,mdtp;
	
	public MNTEntry(String name, int pp, int kp, int mdtp, int kpdtp) {
		super();
		this.macroname = macroname;
		this.pp = pp;
		this.kp = kp;
		this.mdtp = mdtp;
		this.kpdtp = kpdtp;
	}
	
	public String getMacroname() {
		return macroname;
	}
	public void setMacroname(String macroname) {
		this.macroname = macroname;
	}
	public int getKp() {
		return kp;
	}
	public void setKp(int kp) {
		this.kp = kp;
	}
	public int getPp() {
		return pp;
	}
	public void setPp(int pp) {
		this.pp = pp;
	}
	public int getKpdtp() {
		return kpdtp;
	}
	public void setKpdtp(int kpdtp) {
		this.kpdtp = kpdtp;
	}
	public int getMdtp() {
		return mdtp;
	}
	public void setMdtp(int mdtp) {
		this.mdtp = mdtp;
	}
	
	
}