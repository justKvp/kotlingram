package org.acme.services

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.ParseMode
import com.github.kotlintelegrambot.entities.TelegramFile.ByUrl
import com.github.kotlintelegrambot.entities.inputmedia.InputMediaPhoto
import com.github.kotlintelegrambot.entities.inputmedia.MediaGroup
import io.quarkus.runtime.Startup
import jakarta.inject.Singleton
import org.acme.utils.ClassLoader
import java.io.File

//@Singleton
@Startup
class TelegramService {
    private var passwd = "6624855634:AAGebOB3vgonMlqa8RVUpfzHZwq2oPYkI6g"
    private var botPointer: Bot?=null

    constructor() {
        System.out.println("TelegramService initialized")
        botPointer = bot {
            token = passwd
            dispatch {
//                text {
//                    bot.sendMessage(ChatId.fromId(message.chat.id), text = text)
//                }

                command("hello") {
                    var text: String = String.format("Команды, доступные боту:\n/stat nickname")
                    bot.sendMessage(chatId = ChatId.fromId(update.message!!.chat.id), text = text)
                }
                command("stat") {
                    bot.sendAnimation(chatId = ChatId.fromId(update.message!!.chat.id), fileId = "https://ic.pics.livejournal.com/4/59205/14138/14138_original.gif")
                }

                command("markdownV2") {
                    val markdownV2Text = """
                    *bold \*text*
                    _italic \*text_
                    __underline__
                    ~strikethrough~
                    *bold _italic bold ~italic bold strikethrough~ __underline italic bold___ bold*
                    [inline URL](http://www.example.com/)
                    [inline mention of a user](tg://user?id=123456789)
                    `inline fixed-width code`
                    ```kotlin
                    fun main() {
                        println("Hello Kotlin!")
                    }
                    ```
                """.trimIndent()
                    bot.sendMessage(
                            chatId = ChatId.fromId(message.chat.id),
                            text = markdownV2Text,
                            parseMode = ParseMode.MARKDOWN_V2,
                    )
                }

                command("mediaGroup") {
                    bot.sendMediaGroup(
                            chatId = ChatId.fromId(message.chat.id),
                            mediaGroup = MediaGroup.from(
                                    InputMediaPhoto(
                                            media = ByUrl("https://www.sngular.com/wp-content/uploads/2019/11/Kotlin-Blog-1400x411.png"),
                                            caption = "I come from an url :P",
                                    ),
                            ),
                            replyToMessageId = message.messageId,
                    )
                }
            }
        }
        botPointer!!.startPolling()
    }
}