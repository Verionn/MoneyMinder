import SectionHeader from "./sectionHeader";
import './section.css'
const Section = ({ section }) => {
  return (
    <div className="sectionContainer">
        <SectionHeader />
      <h1>Section</h1>
    </div>
  );
};
export default Section;
