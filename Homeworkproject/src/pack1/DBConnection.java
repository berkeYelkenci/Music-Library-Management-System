package pack1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	
	public static Connection getConnection() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/homework","root","B_140721_y!Rem");
		return con;
	}
	
	public boolean checkLogin(String username,String password ) throws SQLException {
		Statement st = getConnection().createStatement();
		ResultSet rs = st.executeQuery("select * from login where username='"+username+"' and password ='"+password+"'");
		if (rs.next()) {
			return true;
		}
		else
			return false;
		}
	
	public void insertArtist(Artist artist) throws SQLException {
		PreparedStatement ps = getConnection().prepareStatement("insert into artists (name, surname, age,img) values (?,?,?,?)");
		ps.setString(1, artist.name);
		ps.setString(2, artist.surname);
		ps.setInt(3, artist.age);
		ps.setString(4, artist.img);
		ps.executeUpdate();
	} 
	
	public void insertSongs(Song song)throws SQLException{
		PreparedStatement ps = getConnection().prepareStatement("insert into songs(title, artist,year,genre,album) values(?,?,?,?,?)");
		ps.setString(1, song.title);
		ps.setString(2, song.artist);
		ps.setInt(3, song.year);
		ps.setString(4, song.genre);
		ps.setString(5, song.album);
		ps.executeUpdate();
	}
	
	public ResultSet getTable(String tableName) throws SQLException {
		Statement st = getConnection().createStatement();
		ResultSet rs = st.executeQuery("select * from "+tableName);
		return rs;
	}
	
	public ResultSet SearchArtist(String value,String sql ) throws SQLException {
		
		Statement st = getConnection().createStatement();
		ResultSet  rs = st.executeQuery(sql);
		return rs;
	}
	
	public ResultSet getArtistName() throws SQLException {
		Statement st = getConnection().createStatement();
		ResultSet rs = st.executeQuery("select name from artists");
		return rs;
	}
	
	public ResultSet getArtistSname() throws SQLException {
		Statement st2 = getConnection().createStatement();
		ResultSet rs2 = st2.executeQuery("select surname from artists");
		return rs2;
	}
	
	public void deleteSongs(Object value) throws SQLException {
		PreparedStatement ps = getConnection().prepareStatement("delete from songs where  idsongs ="+value);
		ps.executeUpdate();
	} 
	
	public void deleteArtists(Object value) throws SQLException {
		PreparedStatement ps = getConnection().prepareStatement("delete from artists where  idartists ="+value);
		ps.executeUpdate();
	} 

	public void updateArtists(String name,String surname,int age,String img,int selected) throws SQLException{
		String sql = "update artists set name = ?, surname= ?, age=? , img=? where idartists = ? ";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setString(1,name);
		ps.setString(2, surname);
		ps.setInt(3,age);
		ps.setString(4, img);
		ps.setInt(5,selected);
		ps.executeUpdate();
	}
	
	public void updateSongs(String title,String artist,String genre,String album,int year,int selected) throws SQLException{
		String sql = "update songs set title = ?, artist = ?,genre = ?,album = ?, year = ? where idsongs = ? ";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setString(1,title);
		ps.setString(2,artist);
		ps.setString(3,genre);
		ps.setString(4,album );
		ps.setInt(5, year);
		ps.setInt(6,selected);
		ps.executeUpdate();
	}
 
}
