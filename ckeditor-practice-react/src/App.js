import React, { useEffect, useState } from "react";
import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import axios from "axios";
import Home from "./pages/Home";
import New from "./pages/New";
import PostDetail from "./pages/PostDetail";

export const PostStateContext = React.createContext();

function App() {
  const [posts, setPosts] = useState([]);
  useEffect(() => {
    axios.get("http://localhost:8080/api/v0/post").then((res) => {
      setPosts(res.data.data);
    });
  }, []);
  return (
    <PostStateContext.Provider value={posts}>
      <BrowserRouter>
        <div className="App">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/:id" element={<PostDetail />} />
            <Route path="/new" element={<New />} />
          </Routes>
        </div>
      </BrowserRouter>
    </PostStateContext.Provider>
  );
}

export default App;
