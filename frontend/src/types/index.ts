import type { Message } from "webstomp-client"
import type { usePlayerStore } from "@/stores/player"
import type { useGuildStore } from "@/stores/guild"

export type PlayerStatus = "playing" | "stopped" | "paused" | "skipping"

export interface DistubeUser {
  id: string
  bot: boolean
  system: boolean
  flags: number
  username: string
  discriminator: string
  avatar: string
  createdTimestamp: string
  defaultAvatarURL: string
  tag: string
  avatarURL: string
  displayAvatarURL: string
  additionalProperties: Map<String, any>
}

export interface Uploader {
  name: string
  url: string
  additionalProperties: Map<String, any>
}

export interface Song {
  source: string
  member: string
  user: DistubeUser
  id: string
  name: string
  isLive: boolean
  duration: number
  formattedDuration: string
  url: string
  streamURL: string
  thumbnail: string
  views: number
  likes: number
  dislikes: number
  uploader: Uploader
  ageRestricted: boolean
  chapters: Array<any>
  reposts: number
  currentTime: number
  additionalProperties: Map<string, any>
}

export type SocketEventType = "play" | "pause" | "stop" | "resume" | "skip" | "add_song" | "add_list" | "queue" | "add_video" | "add_video_next"

export interface EventContext {
  playerStore: ReturnType<typeof usePlayerStore>
  guildStore: ReturnType<typeof useGuildStore>
}

export type EventHandler = Record<SocketEventType, (context: EventContext, event: any) => void>

export type Subscription = Record<string, (message: Message) => void>
export type GuildSubscription = Record<string, (message: Message) => void>

export interface VideoResult {
  etag: string
  id: VideoId
  kind: string
  snippet: VideoSnippet
}

export interface VideoThumbnails {
  default: VideoThumbnailFormat
  high: VideoThumbnailFormat
  medium: VideoThumbnailFormat
}

export interface VideoThumbnailFormat {
  height: number
  url: string
  width: number
}

export interface VideoSnippet {
  channelId: string
  channelTitle: string
  description: string
  liveBroadcastContent: string
  publishTime: string
  publishedAt: string
  thumbnails: VideoThumbnails
  title: string
}

export interface VideoId {
  king: string
  videoId: string
}
