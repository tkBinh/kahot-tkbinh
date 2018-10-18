package com.vietis.kahot.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vietis.kahot.model.Player;

@Repository
@Transactional(rollbackFor = Exception.class)
public class PlayerDAO {

	@Autowired
	public SessionFactory sessionFactory;

	public Session initSession() {
		Session session = sessionFactory.getCurrentSession();
		return session;
	}

	public void save(Player player) {
		try {
			initSession().save(player);
		} catch (Exception e) {
		}
	}

	public void update(Player player) {
		initSession().update(player);
	}

	public List<Player> findByGameId(int gid) {
		List<Player> list;
		try {
			list = initSession().createQuery("select p from Player p where p.game.id =:gid", Player.class)
					.setParameter("gid", gid).getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			list = new ArrayList<>();
		}
		return list;
	}

	public boolean checkDuplicatePlayerName(String name, int gid) {
		List<Player> list = new ArrayList<>();
		try {
			list = initSession()
					.createQuery("select p from Player p where p.username=:name and p.game.id=:gid", Player.class)
					.setParameter("name", name).setParameter("gid", gid).getResultList();
			// if there is another entities with the same name, it means duplicate

		} catch (NoResultException e) {
			e.printStackTrace();

		}
		if (list.size() == 0) {
			return true;
		}
		return false;
	}

	public Player findByNameAndGameId(String name, int gid) {
		Player player;
		player = initSession()
				.createQuery("select p from Player p where p.username=:name and p.game.id=:gid", Player.class)
				.setParameter("name", name).setParameter("gid", gid).getSingleResult();
		return player;
	}
}
