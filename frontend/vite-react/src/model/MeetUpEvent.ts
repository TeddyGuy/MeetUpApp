export interface MeetUpEvent {
    id: number;
    name: string;
    date: Date;
    numberOfConfirmedAttendees: number;
    organizerId: number;
    organizerName: string;
}