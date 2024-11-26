import { useRef } from "react"
import { navigateTo } from "../api/utils.js"
import NavBar from "../common/NavBar.jsx"
import { createPost } from "../api/posts.js"

const HOME_URL = "/"
const CREATE_POST_URL = "/create-post"

export default function CreatePost() {
  return (
    <>
      <NavBar />
      <CreatePostBox />
    </>
  )
}

function CreatePostBox() {
  const topicRef = useRef(null)
  const titleRef = useRef(null)
  const contentRef = useRef(null)

  const sumbitPost = (e) => {
    e.preventDefault();

    const title = titleRef.current.value
    const content = contentRef.current.value
    let topic = topicRef.current.value.trim()

    if (title === "" || content === "") {
      window.alert("Please fill in the contents")
      return
    }

    if (topic === "" || /[^a-zA-Z0-9]/.test(topic)) {
      topic = "general"
    }

    const postData = {
      "post_title": title,
      "content": content,
      "category": topic,
      "attachment_path": "",
      "file_type": "",
    }

    createPost(CREATE_POST_URL, postData, HOME_URL)
  }

  return (
    <div id="create-post-container">
      <form id="create-post-form">
        <div id="email-input" className="input-group">
            {/* TODO to implement getting existing topics, we could store topics in the local storage, or have a root component with topics as its state */}
            <label>Topic (No spaces and special characters): </label>
            <input name="topic" id="topic-input" placeholder="e.g. general" ref={topicRef} />
        </div>

        <div id="password-input" className="input-group">
            <label>Title: </label>
            <input name="title" id="post-title-input" ref={titleRef} />
        </div>

        <div id="password-input" className="input-group">
            <label>Content: </label>
            <textarea name="content-text" id="post-content-input" ref={contentRef} />
        </div>

        <div id="sumbit-post-container">
          <button onClick={() => navigateTo(HOME_URL)}>Cancel</button>
          <button onClick={sumbitPost}>Post</button>
        </div>
      </form>
    </div>
  )
}