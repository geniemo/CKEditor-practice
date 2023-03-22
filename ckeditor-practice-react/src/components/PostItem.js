import { useNavigate } from "react-router-dom";
import { findThumbnail } from "../util/string";

const PostItem = ({ id, title, content }) => {
  const thumbnail = findThumbnail(content);
  const navigate = useNavigate();
  const goDetail = () => {
    navigate(`/${id}`);
  };

  return (
    <div className="PostItem" onClick={goDetail}>
      <div className="thumbnail-wrapper">
        {thumbnail !== null ? <img src={thumbnail} /> : ""}
      </div>
      <div className="title-wrapper">{title}</div>
    </div>
  );
};

export default PostItem;
