import './searchBox.css';
import { SearchIcon } from '../icons/svgIcons';
const SearchBox = () => {
    return (
      <div className="searchBox">
        <input type="text" placeholder="Search" className="searchInput" />
        <SearchIcon className="searchIcon" />
      </div>
    );
  };
  

  export default SearchBox;