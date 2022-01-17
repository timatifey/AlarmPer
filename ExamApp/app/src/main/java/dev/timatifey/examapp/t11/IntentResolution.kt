package dev.timatifey.examapp.t11

object IntentResolution {

    fun match(intent: MyIntent, filter: MyIntentFilter): Boolean {
        if (filter.actions.isNotEmpty()) {
            if (!filter.actions.contains(intent.action)) return false
        } else {
            if (intent.action != null) return false
        }

        if (!intent.categories.containsAll(filter.categories)) return false

        if (filter.type != null) {
            if (filter.type!! != intent.type) return false
        }

        if (filter.scheme != null) {
            val uri = intent.data ?: return false
            if (uri.scheme != filter.scheme) return false
            if (filter.host != null && uri.host != filter.host) return false
            if (filter.port != null && uri.port.toString() != filter.port) return false
            if (filter.path != null) {
                if (uri.path.toString() != filter.path) return false
            }
        }

        return true
    }
}