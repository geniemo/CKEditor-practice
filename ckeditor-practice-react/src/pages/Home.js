import PostList from "../components/PostList";

const Home = ({ posts }) => {
  return (
    <div className="Home">
      <PostList posts={posts} />
    </div>
  );
};

export default Home;
