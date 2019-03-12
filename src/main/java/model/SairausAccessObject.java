package model;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class SairausAccessObject {

	private Istuntotehdas istuntotehdas = null;

	public SairausAccessObject(Istuntotehdas istuntotehdas) {
		this.istuntotehdas = istuntotehdas;

	}

	public boolean createSairaus(Sairaus sairaus) {
		Session istunto = istuntotehdas.openSession();
		Transaction transaktio = null;
		boolean onnistui = false;
		try {
			transaktio = istunto.beginTransaction();
			istunto.saveOrUpdate(sairaus);
			transaktio.commit();
			onnistui = true;
		} catch (Exception e) {
			if (transaktio != null) {
				transaktio.rollback();
			}
		} finally {
			istunto.close();
		}
		return onnistui;
	}

	@SuppressWarnings("unchecked")
	public Sairaus[] readAsiakkaanSairaudet(Asiakas asiakas) {
		Session istunto = istuntotehdas.openSession();
		List<Sairaus> result = null;
		try {
			istunto = istuntotehdas.openSession();
			istunto.beginTransaction();
			String sql = "SELECT * FROM sairaus INNER JOIN asiakas on sairaus.asiakasID = asiakas.asiakasID WHERE asiakas.asiakasID = :id";
			Query<Sairaus> kysely = istunto.createSQLQuery(sql).addEntity(Sairaus.class);
			kysely.setParameter("id", asiakas.getAsiakasID());
			result = kysely.list();
			istunto.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			istunto.close();
		}
		Sairaus[] returnArray = new Sairaus[result.size()];
		return (Sairaus[]) result.toArray(returnArray);
	}

	public boolean deleteSairaus(int id) {
		boolean onnistui = false;
		Session istunto = istuntotehdas.openSession();
		istunto.beginTransaction();
		Sairaus s = (Sairaus) istunto.get(Sairaus.class, id);
		if (s != null) {
			istunto.delete(s);
			onnistui = true;
		}
		istunto.getTransaction().commit();
		istunto.close();
		return onnistui;
	}
}
