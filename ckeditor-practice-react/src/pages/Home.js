import { useContext } from "react";
import { PostStateContext } from "../App";
import PostList from "../components/PostList";

const Home = () => {
  const posts = useContext(PostStateContext);
  return (
    <div className="Home">
      <PostList posts={posts} />
    </div>
  );
};

export default Home;
