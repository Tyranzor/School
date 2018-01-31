package unl.cse.albums;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Album {

	private static Integer albumId;
	private String title;
	private Integer year;
	private Band band;
	private Integer albumNumber;
	private List<String> songTitles = new ArrayList<String>();
	
	public Album(Integer albumId, String title, Integer year, Band band,
			Integer albumNumber) {
		super();
		this.albumId = albumId;
		this.title = title;
		this.year = year;
		this.band = band;
		this.albumNumber = albumNumber;
	}

	public Album(String title, Integer year, String bandName) {
		this(null, title, year, new Band(bandName), null);
	}

	public Integer getAlbumId() {
		return albumId;
	}

	public String getTitle() {
		return title;
	}

	public Integer getYear() {
		return year;
	}

	public Integer getAlbumNumber() {
		return albumNumber;
	}

	public Band getBand() {
		return band;
	}

	public List<String> getSongTitles() {
		return songTitles;
	}

	public void addSong(String songTitle) {
		this.songTitles.add(songTitle);
	}
	
	/**
	 * This method returns a {@link #Album} instance loaded from the 
	 * database corresponding to the given <code>albumId</code>.  
	 * Throws an {@link IllegalStateException} upon an invalid <code>albumId</code>.
	 * All fields are loaded with this method.
	 * @param albumId
	 * @return
	 */
	public static Album getDetailedAlbum(int albumId) {

Album b = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			System.out.println("InstantiationException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.url, DatabaseInfo.username, DatabaseInfo.password);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		

		String query = "SELECT name FROM Album where AlbumId = ?";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, albumId);
			rs = ps.executeQuery();
			if(rs.next()) {
				b = new Album(rs.getString("albumId"), Integer.parseInt(rs.getString("year")),rs.getString("bandId"));
			} else {
				throw new IllegalStateException("no such band with bandId = " + albumId);
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "";
		
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, albumId);
			rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("title"));
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		try {
			if(rs != null && !rs.isClosed())
				rs.close();
			if(ps != null && !ps.isClosed())
				ps.close();
			if(conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return b;
	}
		
	
	
	
	/**
	 * Returns a list of all albums in the database.  However, this
	 * is only a summary so only the following items need to be loaded
	 * from the database:
	 * <ul>
	 *   <li>Album ID</li>
	 *   <li>Album Title</li>
	 *   <li>Album Year</li>
	 *   <li>Band ID</li>
	 *   <li>Band Name</li>
	 * </ul>
	 *   
	 * @return
	 */
	public static List<Album> getAlbumSummaries() {
Album b = null;
ArrayList<Album> albums = new ArrayList<Album>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			System.out.println("InstantiationException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.url, DatabaseInfo.username, DatabaseInfo.password);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		

		String query = "SELECT * FROM Album";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				b = new Album (rs.getInt("albumId"), rs.getString("title"), rs.getInt("year"),Band.getBand(rs.getInt("bandId")), rs.getInt("year"));
				albums.add(b);
			} 
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return null;
		

		
	}

	@Override
	public String toString() {
		return "Album [albumId=" + albumId + ", title=" + title + ", year="
				+ year + ", band=" + band + ", albumNumber=" + albumNumber
				+ ", songTitles=" + songTitles + "]";
	}
	
	
	
	
}
