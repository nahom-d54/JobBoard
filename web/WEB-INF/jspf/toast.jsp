<style>
        .toast-container {
            position: fixed;
            top: 1rem;
            right: 1rem;
            z-index: 9999;
        }
</style>
<div class="toast-container">
            <div id="myToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true" data-bs-autohide="true" data-bs-delay="2000">
                <div class="toast-header">
                    <img src="./assets/icons/radar.svg" class="rounded me-2" alt="...">
                    <strong class="me-auto">Notification</strong>
                    <small class="text-body-secondary">just now</small>
                    <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
                </div>
                <div class="toast-body">
                    ${ param.notification }
                </div>
            </div>
</div>

