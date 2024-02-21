import React from "react";
import { setFonSize } from "../../utils/functions/function";
const Typography = ({ tag = "div", children, ...rest }) => {
  let checkTag = ["h1", "h2", "h3", "h4", "h5", "h6", "p", "span", "div"];
  let isGoodTag = checkTag.includes(tag);
  if (!isGoodTag) tag = "div";
  const Element = tag;

  const styles = {
    fontSize: setFonSize(tag, isGoodTag),
  };

  return (
    <Element style={styles} {...rest}>
      {children}
    </Element>
  );
};

export default Typography;
