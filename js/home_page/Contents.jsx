import api from "../api/axios.config.js"
import { navigateTo, REQ_HEADER } from "../api/utils"
import { useEffect, useState } from "react"
import { parseDate } from "../common/utils.js"

const CREATE_POST_URL = "/create-post"
const GET_USER_URL = "/user-info"

export default function Contents(props) {
  const [displayedPosts, setDisplayedPosts] = useState([])

  useEffect(() => {
    // TODO Temporary solution, should make posts paginable
    const slicedPosts = props.posts.length < perPage
      ? props.posts
      : props.posts.slice(getRandomInt(props.posts.length - perPage), perPage)

    setDisplayedPosts(slicedPosts)
  }, [props.posts])

  const perPage = 20

  return (
    <div id="contents-container">
      <div id="content-header">
        <div>See the latest posts</div>
        <button onClick={() => navigateTo(CREATE_POST_URL)}>+ Create Post</button>
      </div>

      <div id="post-box-container">
        {displayedPosts.map((post, index) => {
          // TODO make the post entity to return a User entity and use that instead of fetching for user
          return <PostBox
            key={index}
            postId={post.entryID}
            authorPfp={""}
            author={post.author}
            timeStamp={parseDate(post.postedDate)}
            title={post.postTitle}
            content={post.content.body}
            topic={post.category}
          />
        })}
      </div>
    </div>
  )
}

function PostBox(props) {
  // see the todo on top
  const [author, setAuthor] = useState(null)

  useEffect(() => {
    api
      .get(`${GET_USER_URL}?user_id=${props.author}`)
      .then(response => {
        setAuthor(response.data.username)
      })
      .catch(error => {
      })
  }, [])

  return (
    <div className="post-box" onClick={() => navigateTo(`/post/${props.postId}`)}>
      <div className="post-box-info">
        <img className="pfp" src={props.authorPfp} alt=" " />
        <div className="post-box-author">{author}</div>
        <div className="post-box-timestamp">{props.timeStamp}</div>
      </div>

      <div className="post-box-title">{props.title}</div>
      <div className="post-box-content">{props.content}</div>
      <div className="post-box-topic">{props.topic}</div>
    </div>
  )
}