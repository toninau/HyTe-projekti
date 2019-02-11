package model;

public interface AsiakasDAO_IF {
	public abstract boolean createAsiakas(Asiakas asiakas);
	public abstract Asiakas readAsiakas(String hetu);
	public abstract Asiakas[] readAsiakas();
	public abstract boolean updateAsiakas(Asiakas asiakas);
	public abstract boolean deleteAsiakas(String hetu);
}
