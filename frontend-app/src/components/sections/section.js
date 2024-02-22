import SectionHeader from "./sectionHeader";
import './section.css'
import {pageSections} from '../../utils/datas/appInfo';
import DisplayRoutes from "../routes/DisplayRoutes";
const Section = ({ section }) => {
  return (
    <div className="sectionContainer">
        <SectionHeader />
        <DisplayRoutes routes={pageSections} />
      <h1>Section</h1>
    </div>
  );
};
export default Section;
