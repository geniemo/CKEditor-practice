import React, { useEffect, useState } from "react";
import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import axios from "axios";
import Home from "./pages/Home";
import New from "./pages/New";

function App() {
  const [posts, setPosts] = useState([]);
  useEffect(() => {
    axios.get("http://localhost:8080/api/v0/post").then((res) => {
      setPosts(res.data.data);
    });
  }, []);
  return (
    <BrowserRouter>
      <div className="App">
        <Routes>
          <Route path="/" element={<Home posts={posts} />} />
          <Route path="/new" element={<New />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
