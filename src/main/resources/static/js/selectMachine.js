document.addEventListener('DOMContentLoaded', () => {
  const buttons = document.querySelectorAll('.main-content__info_machine_column_button');
  const confirmButton = document.querySelector('.main-content__sidebar__button');
  let activeButton = null;

  buttons.forEach(button => {
    button.addEventListener('click', () => {
      // Удаляем класс active со всех кнопок
      buttons.forEach(btn => btn.classList.remove('main-content__info_machine_column_button__active'));
      // Добавляем класс active к нажатой кнопке
      if (!button.classList.contains('main-content__info_machine_column_button__overdue') && !button.classList.contains('main-content__info_machine_column_button__selected') && !button.classList.contains('main-content__info_machine_column_button__occupied')) {
        button.classList.add('main-content__info_machine_column_button__active');
        activeButton = button;
        confirmButton.setAttribute('data-id', button.getAttribute('data'));
        confirmButton.setAttribute('data-time', button.textContent);
      }
    });
  });
});

document.addEventListener('DOMContentLoaded', function() {
  const buttons = document.querySelectorAll('.main-content__info_machine_column_button');
  const now = new Date();

  buttons.forEach(button => {
    const timeText = button.textContent.trim();
    const buttonTime = parseTime(timeText);

    if (buttonTime && buttonTime < now) {
      button.classList.add('main-content__info_machine_column_button__overdue');
    }
  });

  function parseTime(timeString) {
    const [hours, minutes] = timeString.split(':').map(Number);
    if (!isNaN(hours) && !isNaN(minutes)) {
      const now = new Date();
      const time = new Date(now.getFullYear(), now.getMonth(), now.getDate(), hours, minutes);
      return time;
    }
    return null;
  }
});

function writeLaundry(element) {
  const startDate = element.getAttribute('data-time');
  const idWashingMachine = element.getAttribute('data-id');

  const Laundry = {
    time: startDate,
    idWashingMachine: idWashingMachine
  };

  fetch('/dormitory/write', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(Laundry)
  })
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
      })
      .then(() => {
        window.location.href = "http://localhost:8080/dormitory/laundry";
      })
}

function deleteLaundry(element) {
  const laundryId = element.getAttribute('data');

  fetch('/dormitory/deleteLaundry/' + laundryId, {
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
        window.location.href = "http://localhost:8080/dormitory/laundry";
      })
}