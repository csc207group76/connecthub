import NavBar from "../common/NavBar.jsx"
import Topics from "./Topics.jsx"
import Contents from "./Contents.jsx"
import RecentActivities from "./RecentActivities.jsx"
import { useEffect, useState } from "react"
import api from '../api/axios.config.js'

export default function HomePage() {
  return (
    <>
      <NavBar />
      <HomeContent />
    </>
  )
}

function HomeContent() {
  const [posts, setPosts] = useState([])

  useEffect(() => {
    api
    .get("/all-posts")
    .then(response => {
      setPosts(response.data)
    })
    .catch(() => {
      window.alert("Error fetching posts")
    })
  }, [])

  useEffect(() => {}, [posts])

  return (
    <div id="home-page-container">
      <Topics posts={posts} />
      <Contents posts={posts} />
      <RecentActivities />
    </div>
  )
}

