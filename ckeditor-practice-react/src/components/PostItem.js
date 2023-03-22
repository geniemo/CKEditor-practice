import { findThumbnail } from "../util/string";

const PostItem = ({ title, content }) => {
  const thumbnail = findThumbnail(content);
  return (
    <div className="PostItem">
      <div className="thumbnail-wrapper">
        {thumbnail !== null ? <img src={thumbnail} /> : ""}
      </div>
      <div className="title-wrapper">{title}</div>
    </div>
  );
};

export default PostItem;
