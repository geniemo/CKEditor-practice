import { useContext, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { PostStateContext } from "../App";

const PostDetail = () => {
  const { id } = useParams();
  const posts = useContext(PostStateContext);
  const [post, setPost] = useState();
  const navigate = useNavigate();

  useEffect(() => {
    const targetPost = posts.find((it) => parseInt(it.id) === parseInt(id));
    console.log(targetPost);

    if (targetPost) {
      setPost(targetPost);
    } else {
      navigate("/", { replace: true });
    }
  }, [id, posts]);

  if (!post) {
    return <div className="PostDetail">로딩중...</div>;
  } else {
    return (
      <div className="PostDetail">
        <header className="header">
          <div className="title-wrapper">
            <h1>{post.title}</h1>
          </div>
        </header>
        <article className="content">
          <div
            className="content-html"
            dangerouslySetInnerHTML={{ __html: post.content }}
          ></div>
        </article>
      </div>
    );
  }
};

export default PostDetail;
