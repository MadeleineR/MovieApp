package com.example.madeleine.movieapp;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import com.example.madeleine.movieapp.MovieInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MOVIE_INFO".
*/
public class MovieInfoDao extends AbstractDao<MovieInfo, Long> {

    public static final String TABLENAME = "MOVIE_INFO";

    /**
     * Properties of entity MovieInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ImdbID = new Property(1, String.class, "imdbID", false, "IMDB_ID");
        public final static Property Title = new Property(2, String.class, "title", false, "TITLE");
        public final static Property DetailId = new Property(3, Long.class, "detailId", false, "DETAIL_ID");
        public final static Property QueryId = new Property(4, Long.class, "queryId", false, "QUERY_ID");
    };

    private DaoSession daoSession;

    private Query<MovieInfo> query_MovieInfoListQuery;

    public MovieInfoDao(DaoConfig config) {
        super(config);
    }
    
    public MovieInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MOVIE_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"IMDB_ID\" TEXT," + // 1: imdbID
                "\"TITLE\" TEXT," + // 2: title
                "\"DETAIL_ID\" INTEGER," + // 3: detailId
                "\"QUERY_ID\" INTEGER);"); // 4: queryId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MOVIE_INFO\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, MovieInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String imdbID = entity.getImdbID();
        if (imdbID != null) {
            stmt.bindString(2, imdbID);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        Long detailId = entity.getDetailId();
        if (detailId != null) {
            stmt.bindLong(4, detailId);
        }
 
        Long queryId = entity.getQueryId();
        if (queryId != null) {
            stmt.bindLong(5, queryId);
        }
    }

    @Override
    protected void attachEntity(MovieInfo entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public MovieInfo readEntity(Cursor cursor, int offset) {
        MovieInfo entity = new MovieInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // imdbID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // title
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // detailId
            cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4) // queryId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, MovieInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setImdbID(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setTitle(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDetailId(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setQueryId(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(MovieInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(MovieInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "movieInfoList" to-many relationship of Query. */
    public List<MovieInfo> _queryQuery_MovieInfoList(Long queryId) {
        synchronized (this) {
            if (query_MovieInfoListQuery == null) {
                QueryBuilder<MovieInfo> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.QueryId.eq(null));
                query_MovieInfoListQuery = queryBuilder.build();
            }
        }
        Query<MovieInfo> query = query_MovieInfoListQuery.forCurrentThread();
        query.setParameter(0, queryId);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getMovieDetailDao().getAllColumns());
            builder.append(" FROM MOVIE_INFO T");
            builder.append(" LEFT JOIN MOVIE_DETAIL T0 ON T.\"DETAIL_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected MovieInfo loadCurrentDeep(Cursor cursor, boolean lock) {
        MovieInfo entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        MovieDetail movieDetail = loadCurrentOther(daoSession.getMovieDetailDao(), cursor, offset);
        entity.setMovieDetail(movieDetail);

        return entity;    
    }

    public MovieInfo loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<MovieInfo> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<MovieInfo> list = new ArrayList<MovieInfo>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<MovieInfo> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<MovieInfo> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
