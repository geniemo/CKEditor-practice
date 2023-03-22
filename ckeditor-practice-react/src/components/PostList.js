import PostItem from "./PostItem";

const PostList = ({ posts }) => {
  return (
    <div className="PostList">
      {posts.map((it) => (
        <PostItem
          key={it.id}
          id={it.id}
          title={it.title}
          content={it.content}
        />
      ))}
    </div>
  );
};

export default PostList;
