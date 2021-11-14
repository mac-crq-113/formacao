package pt.mac.demo;

public class DemoViews {

	public interface Any {
	}

	public interface User {
		public interface Basic extends Any {
		}
	}

	public interface Playlist {
		public interface Basic extends Any, User.Basic {
		}
	}

	public interface PlaylistEntry {
		public interface Basic extends Any {
		}
	}

}
