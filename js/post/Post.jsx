import { useEffect, useState } from "react"
import api from "../api/axios.config.js"
import NavBar from "../common/NavBar.jsx"
import { formatHandle, parseDate } from "../common/utils.js"


export default function Post() {
  const [postData, setPostData] = useState({
    author: "",
    authorId: "",
    handle: "",
    postTitle: "",
    postBody: "",
    topic: "",
    likes: 0,
    dislikes: 0,
    createdDate: "",
    editedDate: "",
    comments: [],
  })

  useEffect(() => {
    const fetchData = async () => {
      // TODO there should be a better way to do this without making another request to the backend
      // and also utilizing path param. Not gonna worry about this now lol
      const post = await api.get(`/post?post_id=${window.location.href.split("/").slice(-1)[0]}`)

      if (post.data === "") {
        window.alert("Post doesn't exist")
        return
      }
      // TODO again, the backend should return a user entity instead of author id string
      const userData = await api.get(`/user-info?user_id=${post.data.author}`)

      setPostData({
        author: userData.data.username,
        authorId: userData.data.userID,
        handle: formatHandle(userData.data.username),
        postTitle: post.data.postTitle,
        postBody: post.data.content.body,
        topic: post.data.category,
        likes: post.data.likes,
        dislikes: post.data.dislikes,
        createdDate: parseDate(post.data.postedDate),
        editedDate: parseDate(post.data.lastModifiedData),
        comments: post.data.comments,
      })
    }

    fetchData().catch(() => {})
  }, [])

  return (
    <>
      <NavBar />
      <div id="post-info">
        <img className="pfp" src="" alt=" " />
        <div id="post-author-username">{postData.author}</div>
        <div id="post-author-handle">{postData.handle}</div>
        <div id="post-author-created-date">{postData.createdDate}</div>
        {/* <div id="post-author-last-modified">{postData.editedDate}</div> */}
      </div>
      <div id="post-content">
        <div id="post-title">{postData.postTitle}</div>
        <div id="post-topic">{postData.topic}</div>
        <div id="post-body">{postData.postBody}</div>
      </div>
      <div id="post-interaction">
        <button id="like-post">Like</button>
        <button id="dislike-post">Dislike</button>
        <button id="share-post">{"."}</button>
      </div>
      <Comments comments={postData.comments} />
    </>
  )
}

function Comments(props) {
  return (
    <div>Comments</div>
  )
}