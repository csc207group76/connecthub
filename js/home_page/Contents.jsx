import { navigateTo } from "../api/utils"
import { useEffect, useState } from "react"

const CREATE_POST_URL = "/create-post"
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

  const parseDate = (localDateTimeString) => {
    const date = new Date(localDateTimeString);

    const months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];

    const month = months[date.getMonth()];
    const day = date.getDate();
    const year = date.getFullYear();

    return `${month}, ${day}, ${year}`;
  }

  return (
    <div id="contents-container">
      <div id="content-header">
        <div>See the latest posts</div>
        <button onClick={() => navigateTo(CREATE_POST_URL)}>+ Create Post</button>
      </div>

      <div id="post-box-container">
        {displayedPosts.map((post, index) => {
          // TODO get user use case, or edit the post entity to return a User entity
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
  return (
    <div className="post-box" onClick={() => navigateTo(`/post/${props.postId}`)}>
      <div className="post-box-info">
        <img className="post-box-author-pfp" src={props.authorPfp} alt=" " />
        <div className="post-box-author">{props.author}</div>
        <div className="post-box-timestamp">{props.timeStamp}</div>
      </div>

      <div className="post-box-title">{props.title}</div>
      <div className="post-box-content">{props.content}</div>
      <div className="post-box-topic">{props.topic}</div>
    </div>
  )
}