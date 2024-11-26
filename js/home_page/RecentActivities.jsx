export default function RecentActivities(props) {
  return (
    <div id="recent-activity-container">
      <div id="activities-title">RECENT ACTIVITIES</div>
      <div id="activities-container">

      </div>
    </div>
  )
}

function Activity(props) {
  return (
    <div>
      <div className="content-info">
        <img src="" alt=" " /> {/* Content creator pfp */}
        <div className="content-creator">{props.creator}</div>
        <div className="content-timestamp">{props.timestamp}</div>
      </div>

      <div className="activity-action">{props.action}</div>
      <div className="action-content">{props.content}</div>
    </div>
  )
}