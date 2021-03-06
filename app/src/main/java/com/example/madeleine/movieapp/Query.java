package com.example.madeleine.movieapp;

import java.util.List;
import com.example.madeleine.movieapp.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "QUERY".
 */
public class Query {

    private Long id;
    private String searchstring;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient QueryDao myDao;

    private List<MovieInfo> movieInfoList;

    public Query() {
    }

    public Query(Long id) {
        this.id = id;
    }

    public Query(Long id, String searchstring) {
        this.id = id;
        this.searchstring = searchstring;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getQueryDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSearchstring() {
        return searchstring;
    }

    public void setSearchstring(String searchstring) {
        this.searchstring = searchstring;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<MovieInfo> getMovieInfoList() {
        if (movieInfoList == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MovieInfoDao targetDao = daoSession.getMovieInfoDao();
            List<MovieInfo> movieInfoListNew = targetDao._queryQuery_MovieInfoList(id);
            synchronized (this) {
                if(movieInfoList == null) {
                    movieInfoList = movieInfoListNew;
                }
            }
        }
        return movieInfoList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetMovieInfoList() {
        movieInfoList = null;
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}
