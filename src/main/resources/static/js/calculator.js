document.addEventListener('DOMContentLoaded', function() {
    const startDateInput = document.querySelector('.main-content-info_date__select:first-of-type');
    const endDateInput = document.querySelector('.main-content-info_date__select:last-of-type');
    const calculateButton = document.querySelector('.main-content-info_calculate');
    const priceNumber = document.querySelector('.main-content-info_price__number');

    const prices = {
        '01.2024': 1071, // Январь 2024
        '02.2024': 1071, // Февраль 2024
        '03.2024': 1071, // Март 2024
        '04.2024': 1071, // Апрель 2024
        '05.2024': 964,  // Май 2024
        '06.2024': 857,  // Июнь 2024
        '07.2024': 857,  // Июль 2024
        '08.2024': 857,  // Август 2024
        '09.2024': 899,  // Сентябрь 2024
        '10.2024': 1071, // Октябрь 2024
        '11.2024': 1071, // Ноябрь 2024
        '12.2024': 1071, // Декабрь 2024
        '01.2025': 1071, // Январь 2025
        '02.2025': 1071, // Февраль 2025
        '03.2025': 1071, // Март 2025
        '04.2025': 1071, // Апрель 2025
        '05.2025': 964,  // Май 2025
        '06.2025': 857,  // Июнь 2025
        '07.2025': 857,  // Июль 2025
        '08.2025': 857,  // Август 2025
        '09.2025': 899,  // Сентябрь 2025
        '10.2025': 1071, // Октябрь 2025
        '11.2025': 1071, // Ноябрь 2025
        '12.2025': 1071  // Декабрь 2025
    };

    calculateButton.addEventListener('click', function() {
        const startDate = new Date(startDateInput.value);
        const endDate = new Date(endDateInput.value);
        console.log('Start Date:', startDate);
        console.log('End Date:', endDate);

        let totalPayment = 0;

        const currentDate = new Date(startDate);
        while (currentDate <= endDate) {
            const month = currentDate.getMonth();
            const year = currentDate.getFullYear();
            const key = month < 9 ? `0${month + 1}.${year}` : `${month + 1}.${year}`;
            totalPayment += prices[key] || 0;

            currentDate.setMonth(currentDate.getMonth() + 1);

            if (currentDate > endDate) {
                break;
            }
        }

        priceNumber.textContent = totalPayment + ' ₽';
    });
});

function pay() {
    const startDateInput = document.querySelector('.main-content-info_date__select:first-of-type').value;
    const endDateInput = document.querySelector('.main-content-info_date__select:last-of-type').value;
    const priceNumber = parseInt(document.querySelector('.main-content-info_price__number').textContent);

    const Pay = {
        priceNumber: priceNumber,
        startDate: startDateInput,
        endDate: endDateInput
    };

    fetch('/dormitory/pay', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(Pay)
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