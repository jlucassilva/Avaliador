package service.exception

class ServiceException(message: String) : Exception(message) {
    companion object {
        private const val serialVersionUID = 1178421522405346608L
    }
}