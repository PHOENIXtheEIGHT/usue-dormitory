document.querySelector('.main-overlay').addEventListener('click', function(event) {
  if (event.target === this) {
    closePopup();
  }
});

function closePopup() {
  document.querySelectorAll('.popup').forEach(popup => {
    popup.style.display = 'none';
  });
  document.querySelector('.main-overlay').style.display = 'none';
}

function openPopup() {
  document.getElementById('popup').style.display = 'flex';
  document.querySelector('.main-overlay').style.display = 'flex';
}

function saveAnnouncement() {
  const title = document.getElementById('title-announcement').value;
  const content = document.getElementById('content-announcement').value;

  const Announcement = {
    title: title,
    content: content
  };

  fetch('/dormitory/addAnnouncement', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(Announcement)
  })
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
      })
      .then(() => {
        window.location.href = "http://localhost:8080/dormitory/main";
      })
}

function deleteAnnouncement(element) {
  const announcementId = element.getAttribute('data');

  fetch('/dormitory/deleteAnnouncement/' + announcementId, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    }
  })
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
      })
      .then(() => {
        window.location.href = "http://localhost:8080/dormitory/main";
      })
}